package model;

public class JudgeRole implements Role {
    @Override
    public String getRoleName() {
        return "Judge";
    }

    public void assignProblemStatement(Hackathon hackathon, String problemStatement) {
        hackathon.setProblemStatement(problemStatement);
    }

    public void assignFeedback(Upload upload, String feedback) {
        upload.setFeedback(feedback);
    }

    public void assignScore(Team team, int score) {
        team.setScore(score);
    }
}
