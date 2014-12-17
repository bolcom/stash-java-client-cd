package com.bol.cd.stash.fake;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * Used for ssl tests to simplify setup.
 */
public class FakeSSLSocketFactory extends SSLSocketFactory {
    private SSLSocketFactory factory;

    private final static Logger log = LoggerFactory.getLogger(FakeSSLSocketFactory.class);

    public FakeSSLSocketFactory() {
        log.warn("You are using a Fake SSLSocketFactory, this is not recommended for production usage! Please use a valid socket factory instead!");
        try {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{new FakeTrustManager()}, null);
            factory = (SSLSocketFactory) sslcontext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new IllegalStateException("Unable to construct FakeSSLSocketFactory", e);
        }
    }

    public static SocketFactory getDefault() {
        return new FakeSSLSocketFactory();
    }

    private Socket addAnonCipher(Socket socket) {
        SSLSocket ssl = (SSLSocket) socket;
        final String[] ciphers = ssl.getEnabledCipherSuites();
        final String[] anonCiphers = {"SSL_DH_anon_WITH_RC4_128_MD5", "SSL_DH_anon_WITH_RC4_128_MD5", "SSL_DH_anon_WITH_3DES_EDE_CBC_SHA",
                "SSL_DH_anon_WITH_DES_CBC_SHA", "SSL_DH_anon_EXPORT_WITH_RC4_40_MD5", "SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA"};
        final String[] newCiphers = new String[ciphers.length + anonCiphers.length];
        System.arraycopy(ciphers, 0, newCiphers, 0, ciphers.length);
        System.arraycopy(anonCiphers, 0, newCiphers, ciphers.length, anonCiphers.length);
        ssl.setEnabledCipherSuites(newCiphers);
        return ssl;
    }

    public Socket createSocket() throws IOException {
        return addAnonCipher(factory.createSocket());
    }

    public Socket createSocket(Socket socket, String s, int i, boolean flag) throws IOException {
        return addAnonCipher(factory.createSocket(socket, s, i, flag));
    }

    public Socket createSocket(InetAddress inaddr, int i, InetAddress inaddr1, int j) throws IOException {
        return addAnonCipher(factory.createSocket(inaddr, i, inaddr1, j));
    }

    public Socket createSocket(InetAddress inaddr, int i) throws IOException {
        return addAnonCipher(factory.createSocket(inaddr, i));
    }

    public Socket createSocket(String s, int i, InetAddress inaddr, int j) throws IOException {
        return addAnonCipher(factory.createSocket(s, i, inaddr, j));
    }

    public Socket createSocket(String s, int i) throws IOException {
        return addAnonCipher(factory.createSocket(s, i));
    }

    public String[] getDefaultCipherSuites() {
        return factory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return factory.getSupportedCipherSuites();
    }
}
