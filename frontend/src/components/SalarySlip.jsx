export default function SalarySlip({ slip }) {
    return (
      <div>
        <h3>Salary Slip</h3>
        <p>Basic: ₹{slip.basic}</p>
        <p>Tax: ₹{slip.taxDeducted}</p>
        <p>Net Pay: ₹{slip.netPay}</p>
        <button onClick={() => window.print()}>Download</button>
      </div>
    );
  }
  