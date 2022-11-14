package Task_15;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class ForTest_NonblockingServer {
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;

    public ForTest_NonblockingServer(int portForListen) throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(portForListen));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        start();
    }

    private void start() throws IOException {
        SelectionKey key = null;

        while (true) {

            if (selector.select() <= 0) continue;

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                key = iterator.next();
                iterator.remove();
            }

            if (key.isAcceptable()) {
                SocketChannel sc = serverSocketChannel.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                System.out.println("+1 CONNECTION");
            }

            String message = null;
            SocketChannel sc = null;

            if (key.isReadable()) {
                sc = (SocketChannel) key.channel();
                ByteBuffer bb = ByteBuffer.allocate(1024);
                sc.read(bb);

                message = Utils.unwrapMessage(new String(bb.array()).trim());
                //message =new String(bb.array()).trim();

                System.out.println("Message received: " + message);
            }

            if(key.isWritable()){
                if(message != null){
                    sc.write(ByteBuffer.wrap(Utils.wrapMessage(message).getBytes()));
                    System.out.println("Send to client -> " + message);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        int port = 1239;
        new ForTest_NonblockingServer(port);
    }
}
