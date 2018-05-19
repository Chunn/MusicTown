package com.rom.rm.musictown.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.rom.rm.musictown.R;
import com.rom.rm.musictown.dataModel.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity implements OnClickListener {
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button btn_Login;
    private Button btn_SignIn;
    public static List<User> users=new ArrayList<>();
    public static final String USER="user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView=findViewById(R.id.email);
        mPasswordView=findViewById(R.id.password);
        btn_Login=findViewById(R.id.log_in_button);
        btn_SignIn=findViewById(R.id.sign_in_button);
        btn_SignIn.setOnClickListener(this);
        btn_Login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.log_in_button:
                logIn();
                break;
        }
    }
    private void logIn(){
        new GetUser().execute("http://192.168.1.8:14249/api/users");
        String email=mEmailView.getText().toString();

        String pass=mPasswordView.getText().toString();
        Log.d("email",email+""+pass);
        for (int i=0; i<users.size();i++){
            if (users.get(i).getEmail().compareTo(email)==0&&users.get(i).getPassword().compareTo(pass)==0){

            }
        }
        Intent intent=new Intent(LoginActivity.this,UserActivity.class);
        intent.putExtra(USER,email);
        startActivity(intent);



    }
    private  void signIn(){
        String email=mEmailView.getText().toString();
        String pass=mPasswordView.getText().toString();
        new PostUser().execute("http://192.168.1.8:14249/api/users"+"?email="+email+"&pass="+pass);
        Toast.makeText(getApplicationContext(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
        logIn();
    }
    class GetUser extends  AsyncTask<String,Void,Integer>{

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer==400){
                Log.d("Test","Failed");
            }
            else if(integer==200)
            {
                Log.d("Test","Successful");
            }
            super.onPostExecute(integer);
        }
        @Override
        protected Integer doInBackground(String... paramas) {
            String urlString=paramas[0];
            URL url=null;
            HttpURLConnection httpURLConnection=null;
            InputStream inputStream=null;
            String result="";
            int c;
            try {
                url=new URL(urlString);
                httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");

                httpURLConnection.setDoInput(true);
                // đẩy dữ liệu lên server Do Output
                httpURLConnection.connect();
                Log.d("url",urlString);

                //cần luồng đọc dữ liệu đầu vào
                inputStream=httpURLConnection.getInputStream();
                //endoffile=-1
                while ((c=inputStream.read())!=-1){
                    result +=(char)c;

                }
                Log.d("test",result);

                JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject;

                for (int i=0;i<jsonArray.length();i++){
                    Log.d("leng",jsonArray.length()+"");
                    jsonObject=jsonArray.getJSONObject(i);
                    users.add(new User(jsonObject.getInt("idUser"),jsonObject.getString("email"),
                            jsonObject.getString("pass")));
                }

            }catch (Exception ex) {
                ex.printStackTrace();

                return 400;
            }
            return 200;
        }
    }
    class PostUser extends AsyncTask<String,Void,Integer> {

        @Override
        protected void onPostExecute(Integer integer) {
            if (integer == 400) {
                Log.d("Test", "Failed");
            } else if (integer == 200) {
                Log.d("Test", "Successful");
            }
            super.onPostExecute(integer);
        }

        @Override
        protected Integer doInBackground(String... paramas) {
            String urlString = paramas[0];
            URL url = null;
            HttpURLConnection httpURLConnection = null;
            InputStream inputStream = null;
            OutputStream outputStream;
            String result = "";
            int c;
            try {
                url = new URL(urlString);
                httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                // đẩy dữ liệu lên server Do Output
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Type","application/json");
                httpURLConnection.setRequestProperty("Accept","application/json");

                JSONObject jsonObject=new JSONObject();
                jsonObject.put("email",paramas[0]);
                jsonObject.put("pass",paramas[1]);

                outputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                outputStream.write(jsonObject.toString().getBytes(Charset.forName("UTF-8")));
                outputStream.flush();
                outputStream.close();

            } catch (Exception ex) {
                ex.printStackTrace();

                return 400;
            }
            return 200;
        }
    }


}
