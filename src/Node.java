public class Node {
	public long id;
	public int cmdNum;
	public String message;

	public Node(long i, int cmdN, String r){
		this.id = i;
		this.cmdNum = cmdN;
		this.message = r;
	}

	public String toString() {
		return "Command: " + cmdNum + " Thread ID: " + id + ": " + message;
	}
	public void setCommand(int r)
	{
		cmdNum = r;
	}
	public long getThreadId()
	{
		return id;
	}
	public int getCommand()
	{
		return cmdNum;
	}
	public String getMessage()
	{
		return message;
	}
	public void setMessage(String m, int s)
	{
		message =  m + " " +s ;
	}
}