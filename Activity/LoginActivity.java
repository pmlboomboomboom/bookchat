package com.example.saber.bookchat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends Activity {

    /*
     * View Component object
     */
    private EditText userText, passwordText;
    private Button logInButton;
    private TextView registerText, recoveryText;
    private ImageView listImageView;
    private LinearLayout userNameInputLayout;

    /*
     * event happened handler
     */
    private View.OnClickListener logInOCL, registerOCL, recoveryPasswordOCL, listUsersOCL;


    /*
     * value for states
     */
    private boolean isShow = false;
    public static final int RESULT_FOR_NEW_USER = 1;
    /*
     * Test Code
     */
    private PopupWindow pop;
    private PopupAdapter adapter;
    private ListView listView;
    private List<String> userNames;

    /*
     * Activity change
     */
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_login);
        initUserNamesData();
        initOCL();
        initComponent();

    }

    private void initComponent(){
        userText = (EditText) findViewById(R.id.editTextUserName);
        passwordText = (EditText) findViewById(R.id.editTextPassword);
        logInButton = (Button) findViewById(R.id.buttonLogIn);
        registerText = (TextView) findViewById(R.id.textViewNewUser);
        recoveryText = (TextView) findViewById(R.id.textViewFindPassword);
        listImageView = (ImageView)findViewById(R.id.imageViewList);
        userNameInputLayout = (LinearLayout) findViewById(R.id.linearLayout);
        /*
         *  setting the click event
         */

        logInButton.setOnClickListener(logInOCL);
        registerText.setOnClickListener(registerOCL);
        recoveryText.setOnClickListener(recoveryPasswordOCL);
        listImageView.setOnClickListener(listUsersOCL);


    }

    private void initUserNamesData(){

        userNames = new ArrayList<String>();
        userNames.add("Saber");
        userNames.add("Lancer");
        userNames.add("Ayano");
        userNames.add("Asuna");
    }

    private void initOCL(){

        logInOCL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName(LoginActivity.this, XListViewActivity.class);
                mIntent = new Intent();
                mIntent.setComponent(componentName);
                startActivity(mIntent);
                finish();
            }
        };

        registerOCL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComponentName componentName = new ComponentName(LoginActivity.this, RegisterActivity.class);
                mIntent = new Intent();
                mIntent.setComponent(componentName);
                startActivityForResult(mIntent,RESULT_FOR_NEW_USER);
            }
        };

        recoveryPasswordOCL = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };

        listUsersOCL =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pop == null){
                    if (adapter == null){
                        adapter = new PopupAdapter(LoginActivity.this);
                        listView = new ListView(LoginActivity.this);
                        pop = new PopupWindow(listView, userNameInputLayout.getWidth(), ActionBar.LayoutParams.WRAP_CONTENT);
                        listView.setAdapter(adapter);
                        pop.showAsDropDown(userText);
                        isShow = true;
                    }
                } else if (isShow){
                    pop.dismiss();
                    isShow = false;
                } else if (!isShow){
                    pop.showAsDropDown(userText);
                    isShow = true;
                }
            }
        };
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){

        if (requestCode == RESULT_FOR_NEW_USER && intent != null)
        {
            Bundle data = intent.getExtras();
            String name = data.getString("userName");
            String password = data.getString("password");
            Log.i(name, password);
            userText.setText(data.getString("userName"));
            passwordText.setText(data.getString("password"));
            userNameInputLayout.requestFocus();
        }
    }

    class PopupAdapter extends BaseAdapter {

        private Context context;
        private LayoutInflater layoutInflater;

        public PopupAdapter(){

        }

        public PopupAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return userNames.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            final String name = userNames.get(position);
            if (convertView == null){
                layoutInflater = LayoutInflater.from(context);
                convertView = layoutInflater.inflate(R.layout.layout_for_pop_up, null);
                holder = new Holder();
                holder.tv = (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            if (holder != null){

                convertView.setId(position);
                holder.setId(position);
                holder.tv.setText(name);

                holder.tv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        pop.dismiss();
                        isShow = false;
                        userText.setText(name);
                        return true;
                    }
                });
            }

            return convertView;
        }
    }

    class Holder{
        TextView tv;

        void setId(int position) {
            tv.setId(position);
        }
    }

}
