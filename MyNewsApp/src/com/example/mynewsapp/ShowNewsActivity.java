package com.example.mynewsapp;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.OnekeyShareTheme;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.TextSize;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ShareActionProvider;

public class ShowNewsActivity extends Activity implements OnClickListener{
	
	private ImageButton bt_shownews_leftbt;
	private ImageButton bt_shownews_setsize;
	private ImageButton bt_shownews_share;
	private WebView wb_shownews_content;
	private ProgressBar pb_shownews_loading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_shownews);
		
		
		bt_shownews_leftbt = (ImageButton) findViewById(R.id.bt_shownews_leftbt);
		bt_shownews_setsize = (ImageButton) findViewById(R.id.bt_shownews_setsize);
		bt_shownews_share = (ImageButton) findViewById(R.id.bt_shownews_share);
		wb_shownews_content = (WebView) findViewById(R.id.wb_shownews_content);
		pb_shownews_loading = (ProgressBar) findViewById(R.id.pb_shownews_loading);
		
		
		bt_shownews_leftbt.setOnClickListener(this);
		bt_shownews_setsize.setOnClickListener(this);
		bt_shownews_share.setOnClickListener(this);

		Intent intent = getIntent();	
		String url = intent.getStringExtra("url");
		System.out.println("ShowNewsActivity.onCreate()"+url);
		
		settings = wb_shownews_content.getSettings();
		settings.setJavaScriptEnabled(true);
		
		
		wb_shownews_content.setWebViewClient(new WebViewClient(){
			
			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				System.out
						.println("ShowNewsActivity.onCreate(...).new WebViewClient() {...}.onPageFinished()");
				super.onPageFinished(view, url);
			}
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				// TODO Auto-generated method stub
				pb_shownews_loading.setVisibility(View.GONE);

				System.out
						.println("ShowNewsActivity.onCreate(...).new WebViewClient() {...}.onPageStarted()");
				super.onPageStarted(view, url, favicon);
			}
		 
		
			
		});
		
		wb_shownews_content.loadUrl(url);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_shownews_leftbt:
			finish();
			break;

		case R.id.bt_shownews_setsize:
			setTextsize();		
			break;
		case R.id.bt_shownews_share:

			showShare();
			break;
		default:
			break;
		}
		
	}

 
		
		
		private void showShare() {
			 ShareSDK.initSDK(this);
			 OnekeyShare oks = new OnekeyShare();
			 //关闭sso授权
			 oks.disableSSOWhenAuthorize(); 

			// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
			 //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
			 // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
			 oks.setTitle(getString(R.string.share));
			 // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
			 oks.setTitleUrl("http://sharesdk.cn");
			 // text是分享文本，所有平台都需要这个字段
			 oks.setText("我是分享文本");
			 // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
			 oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
			 // url仅在微信（包括好友和朋友圈）中使用
			 oks.setUrl("http://sharesdk.cn");
			 // comment是我对这条分享的评论，仅在人人网和QQ空间使用
			 oks.setComment("我是测试评论文本");
			 // site是分享此内容的网站名称，仅在QQ空间使用
			 oks.setSite(getString(R.string.app_name));
			 // siteUrl是分享此内容的网站地址，仅在QQ空间使用
			 oks.setSiteUrl("http://sharesdk.cn");

			// 启动分享GUI
			 
			 oks.setTheme(OnekeyShareTheme.SKYBLUE);
			 oks.show(this);
			 }
 


	int current_select=2;
	private WebSettings settings;
	private void setTextsize() {
		
		// TODO Auto-generated method stub
		
		String[] sizes={"超大号字体","大号字体","正常字体","小号字体","超小号字体"};
		
		AlertDialog.Builder builder = new Builder(this);
		
		builder.setTitle("字体设置")
		.setSingleChoiceItems(sizes, current_select, new AlertDialog.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				current_select=which;
			}
		})
		.setNegativeButton("取消", null)
		.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (current_select) {
				case 0:
					settings.setTextSize(TextSize.LARGEST);
					break;
				case 1:
					settings.setTextSize(TextSize.LARGER);
					break;
				case 2:
					settings.setTextSize(TextSize.NORMAL);
					break;
				case 3:
					settings.setTextSize(TextSize.SMALLER);
					break;
				case 4:
					settings.setTextSize(TextSize.SMALLEST);
					break;

				default:
					break;
				}
			}
		})
		.show();
		
	}
	
	

}
