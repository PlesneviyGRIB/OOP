package Task_15;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class ForTest_NonblockingServer {
    private final Selector selector;

    private final static AtomicReference<SocketChannel> socketChannelAtomicReference = new AtomicReference<>();
    private final ServerSocketChannel serverSocketChannel;

    public ForTest_NonblockingServer(int portForListen) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(portForListen));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, SelectionKey.OP_READ);

        start();
    }

    private void start() throws IOException {
        SelectionKey key;

        while (true) {

            if (selector.select() <= 0) continue;

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                key = iterator.next();
                iterator.remove();

                if (key.isAcceptable()) {
                    SocketChannel sc = serverSocketChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ, SelectionKey.OP_WRITE);
                    System.out.println("+1 CONNECTION");
                }

                if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    socketChannelAtomicReference.set(sc);
                    ByteBuffer bb = ByteBuffer.allocate(1024);
                    sc.read(bb);

                    String message = new String(bb.array()).trim();

                    System.out.println("Message received: " + message);
                    echo(message, sc);
                }
            }
        }
    }

    private void echo(String str, SocketChannel sc) throws IOException {
        sc.write(ByteBuffer.wrap(str.getBytes()));
        System.out.println("Send to client -> " + str);
    }

    public static void main(String[] args) throws Exception{

        int port = 1239;

        new ForTest_NonblockingServer(port);
    }
}
