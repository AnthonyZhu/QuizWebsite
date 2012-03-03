package quizweb.question;

public abstract class Question {
	protected int questionID;
	public Object question;
	public Object answer;
	public double score;
	
	public abstract double getScore(Object userAnswer);
}
