package com.example.mazhar.g.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Mazhar on 11/09/17.
 */


public class SaveDataInExternalStorage {



    public void writeToFile(String data, String fileName)
    {

        // Make sure the path directory exists.
        if(!Constants.PATH.exists())
        {
            // Make it, if it doesn't exit
            Constants.PATH.mkdirs();
        }

        final File file = new File(Constants.PATH, fileName);

        // Save your stream, don't forget to flush() it before closing it.

        try
        {
            file.createNewFile();
           /* FileOutputStream fOut = new FileOutputStream(file,true);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);

            myOutWriter.close();*/

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file,true));
            byte[] yourKey = new byte[0];
            try {
                yourKey = generateKey("atul_pawar");
                byte[] filesBytes = encodeFile(yourKey, data.trim().getBytes());
                System.out.println("BYTES : "+filesBytes +"STRING BYTES : "+data.getBytes("UTF-8"));
                bos.write(filesBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }

            bos.flush();
            bos.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    public String readFromFile(String fileName)
    {

        final File file = new File(Constants.PATH, fileName);



/*        // Make sure the path directory exists.
        if(file.exists())
        {
//Read text from file
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            //You'll need to add proper error handling here
        }*/


        byte[] contents = null;

        int size = (int) file.length();
        contents = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(
                    new FileInputStream(file));
            try {
                buf.read(contents);
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] yourKey = new byte[0];

            yourKey = generateKey("atul_pawar");
            byte[] decodedData = decodeFile(yourKey, contents);
            System.out.println("STRING  : "+new String(decodedData, "UTF-8"));
            return new String(decodedData, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



    public boolean deleteFile(String fileName)
    {

        final File file = new File(Constants.PATH, fileName);

        // Make sure the path directory exists.
        if(file.exists())
        {
            file.delete();
            return true;
        }
        return false;
    }




    public static byte[] generateKey(String password) throws Exception
    {
        byte[] keyStart = password.getBytes("UTF-8");

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", new CryptoProvider());
        sr.setSeed(keyStart);
        kgen.init(128, sr);
        SecretKey skey = kgen.generateKey();
        return skey.getEncoded();
    }

    public static byte[] encodeFile(byte[] key, byte[] fileData) throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(fileData);

        return encrypted;
    }

    public static byte[] decodeFile(byte[] key, byte[] fileData) throws Exception
    {
        System.out.println("BYTES2 : "+fileData +"STRING BYTES2 : "+new String(fileData, "UTF-8"));

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] decrypted = cipher.doFinal(fileData);

        return decrypted;

    }
    public void saveDataOffline(JSONObject jsonObject, Context context, String fileName)
    {
        try {
            if (!CheckNetworkConnctivity.checkConnectivity(context)) {
                Toast.makeText(context, "Data Saved Offline Successfully!!!", Toast.LENGTH_LONG).show();
                try {
                    jsonObject.put("isOffline", "true");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    jsonObject.put("isOffline", "false");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            String jsonObject1 = jsonObject.toString().trim();
            System.out.println("ARRAY DATA : " + jsonObject1);

            try {
                //if (DataValidation.isNullString(readFromFile(fileName + ".txt"))) {
                writeToFile(jsonObject1.toString().trim(), fileName +"_"+new Constants().VLCC+ ".txt");
           /* } else {
                writeToFile("," + jsonObject1.toString().trim(), fileName + ".txt");

            }*/
            } catch (Exception e) {
                e.printStackTrace();
                writeToFile(jsonObject1.toString().trim(), fileName +"_"+new Constants().VLCC+ ".txt");

            }

        }catch (Exception e){e.printStackTrace();}
    }

}
