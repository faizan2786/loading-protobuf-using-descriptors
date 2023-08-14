package proto.example;

import com.google.protobuf.DescriptorProtos.FileDescriptorSet;
import com.google.protobuf.DescriptorProtos.FileDescriptorProto;
import com.google.protobuf.Descriptors.Descriptor;
import com.google.protobuf.Descriptors.FileDescriptor;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileDescriptorDemo {

    public static void main (String[] args) {

        if (args.length != 1) {
            System.out.println("Descriptor file name argument missing!");
            return;
        }

        final String filePath = args[0];
        FileDescriptorDemo fdDemo = new FileDescriptorDemo();
        fdDemo.loadDescriptors(filePath);
    }

    /* Load protobuf descriptor file in order to get the message definitions
       defined in a .proto file
     */
    private void loadDescriptors(String filePath) {
        try {
            // read the .proto file into a stream
            InputStream fileStream = FileDescriptorDemo.class.getResourceAsStream(filePath);

            if (fileStream == null)
            {
                System.out.printf("Given protobuf descriptor file \"%s\" not found!\n", filePath);
                return;
            }

            FileDescriptorSet fileDescriptorSet = FileDescriptorSet.parseFrom(fileStream);

            for (FileDescriptorProto fileDescriptorProto : fileDescriptorSet.getFileList()) {
                FileDescriptor fileDescriptor = FileDescriptor.buildFrom(fileDescriptorProto, new FileDescriptor[0]);

                for (Descriptor messageDescriptor : fileDescriptor.getMessageTypes()) {
                    System.out.println("Message Name: " + messageDescriptor.getName());
                }
            }

            fileStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
