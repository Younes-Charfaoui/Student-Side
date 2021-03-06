/*------------------------------------------------------------------------------
 - Copyright (c) 2018. This code was created by Younes Charfaoui in the process of Graduation Project for the year of  2018 , which is about creating a platform  for students and professors to help them in the communication and the get known of the university information and so on.
 -----------------------------------------------------------------------------*/

package com.ibnkhaldoun.studentside.security;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

/**
 * @definition this class has the method for
 * do the security side of the client app,
 * that include the basic hashing things and encrypt the data
 * and decrypt it.
 */

public class CryptographyUtilities {

    public static String encryptString(String text, SecretKey key) {
        // TODO: 09/03/2018 add the code to encrypt the data

        return null;
    }

    public static String decryptString(String data) {
        // TODO: 09/03/2018 add the code to decrypt the data
        return data;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String hashingPassword(String password, String salt) {

        return Hashing.sha256()
                .hashString(password + salt, StandardCharsets.UTF_8)
                .toString();
    }
}
