package com.example.mazhar.g.interfaces;

import com.example.mazhar.g.utils.InputStreamVolleyRequest;

/**
 * Created by Mazhar on 20/09/17.
 */

public interface IMultiPartParserInterface {

        public void parseMultiPartResult(InputStreamVolleyRequest request, byte[] bytes, String webserviceName);

}
