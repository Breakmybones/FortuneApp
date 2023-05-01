//import okhttp3.Interceptor
//import okhttp3.Response
//import java.io.IOException
//import java.net.InetAddress
//import java.net.Socket
//import javax.net.ssl.SSLContext
//import javax.net.ssl.SSLSocket
//import javax.net.ssl.SSLSocketFactory
//
//class SSLv3RemovalInterceptor : Interceptor {
//    @Throws(IOException::class)
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//
//        val sslContext = SSLContext.getInstance("TLSv1.2")
//        sslContext.init(null, null, null)
//        val sslSocketFactory = sslContext.socketFactory
//
//        val socket = sslSocketFactory.createSocket() as SSLSocket
//
//        val supportedProtocols = socket.supportedProtocols
//        val enabledProtocols = socket.enabledProtocols
//        val protocols = enabledProtocols.filter { it != "SSLv3" }.toTypedArray()
//
//        socket.enabledProtocols = protocols
//
//        val sslSocketFactory2 = object : SSLSocketFactory() {
//            override fun getDefaultCipherSuites(): Array<String> {
//                return sslSocketFactory.defaultCipherSuites
//            }
//
//            override fun getSupportedCipherSuites(): Array<String> {
//                return sslSocketFactory.supportedCipherSuites
//            }
//
//            override fun createSocket(
//                s: String?,
//                i: Int,
//                inetAddress: java.net.InetAddress?,
//                i1: Int
//            ): SSLSocket {
//                val socket = sslSocketFactory.createSocket(s, i, inetAddress, i1) as SSLSocket
//                socket.enabledProtocols = protocols
//                return socket
//            }
//
//            override fun createSocket(inetAddress: java.net.InetAddress?, i: Int): SSLSocket {
//                val socket = sslSocketFactory.createSocket(inetAddress, i) as SSLSocket
//                socket.enabledProtocols = protocols
//                return socket
//            }
//
//            override fun createSocket(
//                address: InetAddress?,
//                port: Int,
//                localAddress: InetAddress?,
//                localPort: Int,
//            ): Socket {
//                TODO("Not yet implemented")
//            }
//
//            override fun createSocket(
//                s: String?,
//                i: Int,
//                inetAddress: java.net.InetAddress?,
//                i1: Int,
//                proxy: java.net.Proxy?
//            ): SSLSocket {
//                val socket = sslSocketFactory.createSocket(s, i, inetAddress, i1, proxy) as SSLSocket
//                socket.enabledProtocols = protocols
//                return socket
//            }
//
//            override fun createSocket(
//                s: Socket?,
//                host: String?,
//                port: Int,
//                autoClose: Boolean,
//            ): Socket {
//                TODO("Not yet implemented")
//            }
//
//            override fun createSocket(): SSLSocket {
//                val socket = sslSocketFactory.createSocket() as SSLSocket
//                socket.enabledProtocols = protocols
//                return socket
//            }
//
//            override fun createSocket(host: String?, port: Int): Socket {
//                TODO("Not yet implemented")
//            }
//        }
//
//        val client = chain.client.newBuilder()
//            .sslSocketFactory(sslSocketFactory2, sslContext.getSocketFactory().trustManagers[0] as javax.net.ssl.X509TrustManager)
//            .build()
//
//        val newRequest = request.newBuilder().build()
//        return client.newCall(newRequest).execute()
//    }
//}