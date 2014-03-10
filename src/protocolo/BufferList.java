//package protocolo;
//
//public class BufferList {
//	No head;
//	No tail;
//	
//	public BufferList(){
//		head = null;
//		tail = null;
//	}
//	
//	public void insert(byte[] data){
//		
//		if (head == null){
//			head = new No(data);
//			head = tail;
//		}else{
//			tail.next =  new No(data);
//			tail = tail.next;
//		}
//		
//	}
//		
//	public byte[] remove(){
//		
//		byte[] data = null;
//		
//		if(head != null){
//			data = head.data;
//			head = head.next;
//		}
//		
//		return data;
//	}
//	
//}
