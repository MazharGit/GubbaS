package ssg1.gubba1.gubba1.g.interfaces;

import ssg1.gubba1.gubba1.g.utils.InputStreamVolleyRequest;

/**
 * Created by Mazhar on 20/09/17.
 */

public interface IMultiPartParserInterface {

        public void parseMultiPartResult(InputStreamVolleyRequest request, byte[] bytes, String webserviceName);

}
