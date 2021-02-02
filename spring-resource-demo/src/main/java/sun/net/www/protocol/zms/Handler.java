package sun.net.www.protocol.zms;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * @author zms
 * @date 5:06 下午 2021/1/28
 */
public class Handler extends URLStreamHandler {

    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new ZmsURLConnection(u);
    }
}
