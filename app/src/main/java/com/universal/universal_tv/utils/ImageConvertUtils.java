package com.universal.universal_tv.utils;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import com.universal.universal_tv.BuildConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2018/1/31.
 * 图片路径转换相关
 */

public class ImageConvertUtils {

    private static String authority = BuildConfig.authorities;

    /**
     * 文件转绝对路径
     *
     * @param file
     * @return
     */
    public static String fileToPath(@NonNull File file) {
        return file.getAbsolutePath();
    }

    /**
     * 绝对路径转文件
     *
     * @param path
     * @return
     */
    public static File pathToFile(@NonNull String path) {
        File file = new File(path);
        if (isFileExists(file)) {
            return file;
        } else {
            throw new RuntimeException("文件不存在");
        }
    }

    public static Uri pathToUri(@NonNull String path, @NonNull Context context) {
        return fileToUri(new File(path), context);
    }

    /**
     * File转Uri 兼容android 7.0,需要先定义FileProvider
     *
     * @param file      图片文件
     * @param context
     * @return
     */
    public static Uri fileToUri(File file, @NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //android7.0
            return FileProvider.getUriForFile(context, authority, file);
        } else {
            // > 7.0
            return Uri.fromFile(file);
        }
    }

    /**
     * uri转bitmap
     *
     * @param context
     * @param uri
     * @return
     */
    public static Bitmap uriToBitmap(Context context, Uri uri) {
        ContentResolver cr = context.getContentResolver();
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(cr, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * path转bitmap(直接从数据库读取) 兼容android7.0
     *
     * @param path
     * @param context
     * @return
     */
    public static Bitmap pathToBitmap(String path, @NonNull Context context) {
        return uriToBitmap(context, fileToUri(pathToFile(path), context));
    }

    /**
     * path转bitmap(直接转换)
     *
     * @param path
     * @return
     */
    public static Bitmap pathToBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 先获取原大小
        Bitmap mBitmap = BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false; // 获取新的大小

        int sampleSize = 1;
        options.inSampleSize = sampleSize;
        return BitmapFactory.decodeFile(path, options);

    }


    /**
     * bitmap转file
     *
     * @param context
     * @param bitmap
     * @return 注意，建议在其他线程中执行此方法，避免阻塞主线程
     */
    public static File bitmapToFile(@NonNull Context context, @NonNull Bitmap bitmap) {
        String cacheFileNmame = System.currentTimeMillis() + (int) (Math.random() * 1000) + ".jpg";
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), cacheFileNmame);
        try {
            FileOutputStream fileOutStream = null;
            fileOutStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutStream); // 把位图输出到指定的文件中
            fileOutStream.flush();
            fileOutStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * 判断文件是否存在
     *
     * @param file 文件
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }


    /**
     * uri 转path
     *
     * @param context
     * @param uri
     * @return
     */
    public static String uriToPath(Context context,Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
            if (data == null) {
                data = getImageAbsolutePath(context, uri);
            }

        }
        return data;
    }

    private static String getImageAbsolutePath(Context context, Uri imageUri) {
        if (context == null || imageUri == null)
            return null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[] { split[1] };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri))
                return imageUri.getLastPathSegment();
           else {
                String[] projection = { MediaStore.Images.Media.DATA };
                String urlpath;
                CursorLoader loader = new CursorLoader(context,imageUri, projection, null, null, null);
                Cursor cursor = loader.loadInBackground();
                try
                {
                    int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    urlpath =cursor.getString(column_index);
                    //如果是正常的查询到数据库。然后返回结构
                    return urlpath;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    // TODO: handle exception
                }finally{
                    if(cursor != null){
                        cursor.close();
                    }
                }
//                //如果是文件。Uri.fromFile(File file)生成的uri。那么下面这个方法可以得到结果
                urlpath = imageUri.getPath();
                return urlpath;
            }
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = { column };
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
