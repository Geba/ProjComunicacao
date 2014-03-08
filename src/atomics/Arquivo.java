package atomics;

public class Arquivo {

	private byte[] bytes;
	private long id;
	private String path;
	
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Arquivo(byte[] bytes, long id, String path) {
		super();
		this.bytes = bytes;
		this.id = id;
                
                System.out.println("dentro do const: "+path);
		
                for (int i=0; i < path.length(); i++){
                    if(path.charAt(i)=='\n'){
                        this.path = path.substring(i+1, path.length());
                    }
                }
                
                System.out.println("dentro do const: "+this.path);
                
                //this.path = path;
                
                
                      
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
