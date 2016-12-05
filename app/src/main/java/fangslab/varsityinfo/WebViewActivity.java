package fangslab.varsityinfo;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;

import im.delight.android.webview.AdvancedWebView;

public class WebViewActivity extends AppCompatActivity
        implements AdvancedWebView.Listener {
    private AdvancedWebView mWebView;

    public static final int REQUEST_SELECT_FILE = 100;
    public ValueCallback<Uri[]> uploadMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.loadUrl("http://www.uploadion.com/");

        //To exit
        if (getIntent().getBooleanExtra("EXIT", false)) {
            WebViewActivity.this.finish();
            System.exit(0);
        }



    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }


    // Return here when file selected from camera or from SDcard


    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_SELECT_FILE) {
            if (uploadMessage == null) return;
            uploadMessage.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, intent));
            uploadMessage = null;
        }
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }



    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) {
            return;
        }
        // ...
        super.onBackPressed();
    }


    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {
        //To Prevent  Web page not available
        if (errorCode == -2) {
            mWebView.loadData("", "", null);
            //To Show Alert Dialog
            //SplashScreenActivity.class is the Launcher Activity
            // In Case of Frament instead of Activity Replace ClassName.this and getApplicationContext() with getActivity()

            AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
            builder.setCancelable(false);
            builder.setTitle(Html.fromHtml("<font color='#7F02AE'><b>Varsity Info</b></font>"));
            builder.setMessage(Html.fromHtml("<font color='#120049'>Your data services are not working.Please check your internet connection.</font>"));
            builder.setPositiveButton(Html.fromHtml("<font color='#7F02AE'><b>OK</b></font>"), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //SplashScreenActivity.class is your Launcher Activity
                    // In Case of Fragment instead of Activity Replace getApplicationContext()  with getActivity()

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("EXIT", true);
                    startActivity(intent);


                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }


    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }
}
