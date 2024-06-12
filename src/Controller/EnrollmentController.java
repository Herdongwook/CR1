package Controller;

public class EnrollmentController implements ActionListener {
    private EnrollmentView enrollmentView;

    public EnrollmentController(EnrollmentView enrollmentView) {
        this.enrollmentView = enrollmentView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String operator = ((JButton) e.getSource()).getText();
        if (operator.equals("Enroll")) {
            enroll();
        } else if (operator.equals("Cancel")) {
            cancel();
        }
    }

    private void enroll() {
        UserDTO user = ClassManager.getInstance().getLoggedInUser();
        String selectedCourse = enrollmentView.getSelectedCourse();

        if (user != null && user.getType().equals("student")) {
            // 수강신청 로직
            ClassManager.getInstance().getDAO().enrollCourse(user.getId(), selectedCourse);
            JOptionPane.showMessageDialog(null, "수강신청이 완료되었습니다.");
        } else {
            JOptionPane.showMessageDialog(null, "로그인 정보가 없거나 학생 계정이 아닙니다.");
        }
    }

    private void cancel() {
        UserDTO user = ClassManager.getInstance().getLoggedInUser();
        String selectedCourse = enrollmentView.getSelectedCourse();

        if (user != null && user.getType().equals("student")) {
            // 수강 취소 로직
            ClassManager.getInstance().getDAO().cancelCourse(user.getId(), selectedCourse);
            JOptionPane.showMessageDialog(null, "수강 취소가 완료되었습니다.");
        } else {
            JOptionPane.showMessageDialog(null, "로그인 정보가 없거나 학생 계정이 아닙니다.");
        }
    }
}
