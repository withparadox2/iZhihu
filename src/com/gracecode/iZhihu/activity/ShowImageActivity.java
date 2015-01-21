package com.gracecode.iZhihu.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gracecode.iZhihu.R;
import com.gracecode.iZhihu.util.ImageLoaderUtil;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ShowImageActivity extends BaseActivity {
	
	private ImageView imageView;
	private ProgressBar progressBar;
	private TextView tvError;
	private TextView tvLoading;
	
	private String imageUrl;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_photoview);
		imageView = (ImageView) findViewById(R.id.image);
		tvLoading = (TextView) findViewById(R.id.tv_loading);
		tvError = (TextView) findViewById(R.id.tv_error);
		progressBar = (ProgressBar) findViewById(R.id.pb_wait);
		imageUrl = getIntent().getStringExtra("url");
		actionBar.setDisplayHomeAsUpEnabled(true);
		ImageLoaderUtil.displayImage(imageUrl, imageView, new ImageLoadingListener() {
			@Override
			public void onLoadingStarted(String imageUri, View view) {
				tvError.setVisibility(View.INVISIBLE);
				tvLoading.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				tvLoading.setVisibility(View.INVISIBLE);
				tvError.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
				// TODO Auto-generated method stub
				tvLoading.setVisibility(View.INVISIBLE);
				imageView.setVisibility(View.VISIBLE);
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				tvLoading.setVisibility(View.INVISIBLE);
			}
		});
	}
}
