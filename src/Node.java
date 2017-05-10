public class Node {
	public int id;
	public int cmdNum;
	public String request;

	public Node(int i, int cmdN, String r){
		this.id = i;
		this.cmdNum = cmdN;
		this.request = r;
	}

	public String toString() {
		return "Interation: " + cmdNum + " Thread ID: " + id + ": " + request;
	}
	public void setCommand(int r)
	{
		cmdNum = r;
	}
	public int getThreadId()
	{
		return id;
	}
	public int getCommand()
	{
		return cmdNum;
	}
	public String getRequest()
	{
		return request;
	}
}