package com.gracecode.iZhihu.util;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.gracecode.iZhihu.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ImageLoaderUtil {

	public static ImageLoader imageLoader;
	public static DisplayImageOptions options;

	public static void init(Context context) {

		int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
		int cacheSize = 1024 * 1024 * memClass / 8;
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPoolSize(3)
				.threadPriority(Thread.MAX_PRIORITY).memoryCacheSize(cacheSize)
				.memoryCache(new LruMemoryCache(2 * 1024 * 1024)).diskCacheSize(20 * 1024 * 1024)
				.denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();

		imageLoader = ImageLoader.getInstance();
		imageLoader.init(config);

		options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.ic_launcher)
				.showImageOnLoading(R.drawable.ic_launcher).showImageOnFail(R.drawable.ic_launcher)
				.resetViewBeforeLoading(false)
				.cacheInMemory(true).cacheOnDisk(true).bitmapConfig(Bitmap.Config.RGB_565).build();
	}

	public static void displayImage(String url, ImageView imageView, ImageLoadingListener listener) {
		if (imageLoader == null) {
			init(imageView.getContext());
		}
		imageLoader.displayImage(url, imageView, options, listener);
	}

	public static void clearCache() {
		imageLoader.clearDiskCache();
		imageLoader.clearMemoryCache();
	}

}
