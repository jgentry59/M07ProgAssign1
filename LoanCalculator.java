import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoanCalculator extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve input parameters from the form
        double loanAmount = Double.parseDouble(request.getParameter("loanAmount"));
        double annualInterestRate = Double.parseDouble(request.getParameter("interestRate"));
        int numOfYears = Integer.parseInt(request.getParameter("numOfYears"));

        // Calculate monthly interest rate and number of payments
        double monthlyInterestRate = (annualInterestRate / 100) / 12;
        int numOfPayments = numOfYears * 12;

        // Calculate monthly payment
        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numOfPayments));

        // Calculate total payment
        double totalPayment = monthlyPayment * numOfPayments;

        // Pass results to a JSP for rendering
        request.setAttribute("loanAmount", loanAmount);
        request.setAttribute("annualInterestRate", annualInterestRate);
        request.setAttribute("numOfYears", numOfYears);
        request.setAttribute("monthlyPayment", monthlyPayment);
        request.setAttribute("totalPayment", totalPayment);

        RequestDispatcher dispatcher = request.getRequestDispatcher("LoanCalculator.jsp");
        dispatcher.forward(request, response);
    }
}
