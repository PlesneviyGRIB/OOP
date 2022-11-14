package Task_15;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EchoNonblockingServer {
    private final Selector selector;
    private final ServerSocketChannel serverSocketChannel;
    private final String host;
    private final int portForTransfer;

    private final Map<SocketChannel, NonblockingClient> map = new HashMap<>();

    public EchoNonblockingServer(int portForListen, String nodeName, int portForTransfer) throws Exception {
        this.host = nodeName;
        this.portForTransfer = portForTransfer;
        
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(portForListen));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        start();
    }
    
    private void start() throws Exception {
        SelectionKey key = null;
        SocketChannel sc = null;

        while (true) {

            if (selector.select() <= 0) continue;

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()) {
                key = iterator.next();
                iterator.remove();
            }

            if (key.isAcceptable()) {
                sc = serverSocketChannel.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

                var connection = new NonblockingClient(host, portForTransfer, sc);

                map.put(sc, connection);
                connection.start();

                System.out.println("+1 CONNECTION");
            }

            if (key.isReadable()) {
                sc = (SocketChannel) key.channel();
                ByteBuffer bb = ByteBuffer.allocate(1024);

                sc.read(bb);

                String message = Utils.unwrapMessage(new String(bb.array()).trim());
                System.out.println("Message received from CLIENT: " + message);

                try {
                    map.get(sc).sendMessage(Utils.wrapMessage(message));
                }catch (Exception e){
                    System.out.println("SERVER UNREACHABLE. Pipe TERMINATED");
                    sc.socket().close();
                    continue;
                }

                System.out.println("TO-SERVER: " + message);
            }

            if(key.isWritable()) {
                String str;
                if (!(str = map.get(sc).receiveMessage()).equals("")) {
                    str = Utils.unwrapMessage(str);
                    System.out.println("FROM-SERVER: " + str);
                    sc.write(ByteBuffer.wrap(Utils.wrapMessage(str).getBytes()));
                    System.out.println("Transferred to CLIENT: " + str);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        String host = "localhost";
        int port = 1238;
        int port1 = 1239;

        if(args.length == 3){
            host = args[0];
            port = Integer.parseInt(args[1]);
            port1 = Integer.parseInt(args[2]);
        }

        new EchoNonblockingServer(port, host, port1);
    }
}