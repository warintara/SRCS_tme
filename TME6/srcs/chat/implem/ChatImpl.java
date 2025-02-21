package srcs.chat.implem;

import java.util.HashMap;
import java.util.Map;

import com.google.protobuf.BoolValue;
import com.google.protobuf.Empty;
import com.google.protobuf.Int32Value;
import com.google.protobuf.StringValue;

import io.grpc.stub.StreamObserver;
import srcs.ChatGrpc.ChatImplBase;
import srcs.mes;
import srcs.user;

public class ChatImpl extends ChatImplBase {
	
	private Map<String,String> mapstring=new HashMap<>();

	@Override
	public void subscribe(user request, StreamObserver<BoolValue> responseObserver) {
		String user = request.getUsername();
		if(mapstring.containsKey(user)) {
			responseObserver.onNext(BoolValue.of(false));
		}
		else {
			mapstring.put(user,null);
			responseObserver.onNext(BoolValue.of(false));
		}
	}

	@Override
	public void send(mes request, StreamObserver<Int32Value> responseObserver) {
		// TODO Auto-generated method stub
		super.send(request, responseObserver);
	}

	@Override
	public void listChatter(Empty request, StreamObserver<StringValue> responseObserver) {
		
	}

	@Override
	public void unsubscribe(StringValue request, StreamObserver<Empty> responseObserver) {
		// TODO Auto-generated method stub
		super.unsubscribe(request, responseObserver);
	}

}
