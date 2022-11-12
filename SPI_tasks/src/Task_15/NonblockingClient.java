package Task_15;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NonblockingClient {
    private final SocketChannel sc;
    private final SocketChannel socketChannel;
    private final Selector selector;

    public NonblockingClient(String nodeName, int port, SocketChannel socketChannel) throws Exception {
        this.socketChannel = socketChannel;
        selector = Selector.open();

        sc = SocketChannel.open();
        sc.configureBlocking(false);
        sc.connect(new InetSocketAddress(InetAddress.getByName(nodeName), port));
        sc.register(selector, SelectionKey.OP_CONNECT, SelectionKey.OP_WRITE);
    }

    public void start() throws Exception {
        if (selector.select() > 0) processReadySet(selector.selectedKeys());
    }


    private Boolean processReadySet(Set<SelectionKey> readySet) throws Exception {
        SelectionKey key = null;

        Iterator<SelectionKey> iterator = readySet.iterator();

        while (iterator.hasNext()) {
            key = iterator.next();
            iterator.remove();


            if (key.isConnectable())
                if (!processConnect(key)) return true;

            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer bb = ByteBuffer.allocate(1024);
                sc.read(bb);

                socketChannel.write(bb);
            }

            if (key.isWritable()) {
                ByteBuffer bb = ByteBuffer.allocate(1024);
                socketChannel.read(bb);

                SocketChannel sc = (SocketChannel) key.channel();
                sc.write(bb);
            }
        }

        return false;
    }

    public void sendMessage(String message) throws Exception {
        sc.write(ByteBuffer.wrap(message.getBytes()));
    }

    public String receiveMessage() throws Exception {
        TimeUnit.MILLISECONDS.sleep(20);
        ByteBuffer bb = ByteBuffer.allocate(1024);
        sc.read(bb);
        return new String(bb.array()).trim();
    }

    private Boolean processConnect(SelectionKey key) {
        SocketChannel sc = (SocketChannel) key.channel();
        try {
            while (sc.isConnectionPending())
                sc.finishConnect();
        } catch (IOException e) {
            key.cancel();
            return false;
        }
        return true;
    }
}
