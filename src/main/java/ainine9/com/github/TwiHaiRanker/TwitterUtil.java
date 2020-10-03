package ainine9.com.github.TwiHaiRanker;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

public class TwitterUtil {

    private static String CK = "dHYTqPQTcywUahDe23411abzd";
    private static String CS = "EOfYbkFGHX5haJbhmHAXrA9BAk1lPBhehqWzgl7tdYWllYab0h";
    private static String AT = "3150076898-DbSNvboOajXx2apjqIaUJxvIJMrpA5OwHW2RBJu";
    private static String ATS = "osLtYOmIe8y3njHMyKBLJgu1zbSgvuMBRr0nzDF4avzJt";

    private String count = null;

    public TwitterUtil(String ID) throws Exception {
        CommonsHttpOAuthConsumer consumer = null;
        consumer = new CommonsHttpOAuthConsumer(CK, CS);
        consumer.setTokenWithSecret(AT, ATS);

        HttpParams parameters = new BasicHttpParams();
        HttpProtocolParams.setVersion(parameters, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(parameters, HTTP.DEFAULT_CONTENT_CHARSET);
        HttpProtocolParams.setUseExpectContinue(parameters, false);
        HttpConnectionParams.setTcpNoDelay(parameters, true);
        HttpConnectionParams.setSocketBufferSize(parameters, 8192);

        HttpClient httpClient = new DefaultHttpClient();

        SchemeRegistry schReg = new SchemeRegistry();
        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        ClientConnectionManager tsccm = new ThreadSafeClientConnManager(parameters, schReg);

        httpClient = new DefaultHttpClient(tsccm, parameters);

        HttpGet get = new HttpGet("https://api.twitter.com/1.1/statuses/user_timeline.json");

        consumer.sign(get);
        String response = httpClient.execute(get, new BasicResponseHandler());
        httpClient.getConnectionManager().shutdown();

        JSONObject jsonObject = new JSONObject(response);

        count = jsonObject.get("statuses_count").toString();
    }

    public String getCount() {
        return count;
    }
}
