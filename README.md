# loading-protobuf-using-descriptors
This repot demonstrates how to read and load protobuf definitions at run-time using protobuf descriptors in Java

## How to run using custom descriptor files
- Generate protobuf `descriptor-set` file of your `.proto` files using prtobuf compilers using following command:\
  `protoc -I=\path\to\proto-files\ --descriptor_set_out=\path\to\output-file.proto input_file1.proto [input_file2.proto ...]`
  
- copy the generated descriptor file in the `resources` folder
  
- Generate jar package usign: `mvn clean package` command.
  
- execute the jar file by providing descriptor file name in the argument:\
`java -jar file-descriptor-1.0-SNAPSHOT-jar-with-dependencies descriptor-file-name.proto`
