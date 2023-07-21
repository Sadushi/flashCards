
public class FlashCard {
	
	private String question;
	
	 
	public FlashCard(String q, String a) {
		question = q;
		answer = a;
		
	}
	
	//Getters and setters
	
	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getAnswer() {
		return answer;
	}


	public void setAnswer(String answer) {
		this.answer = answer;
	}


	private String answer;
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
