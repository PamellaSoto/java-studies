import api from "../services/api";
import ExpenseCard from "./ExpenseCard";
import { useEffect, useState } from 'react';

const ExpensesList = () => {
  const [expenses, setExpenses] = useState([]); 
  const token = localStorage.getItem("token");

  const handleFetchExpenses = async () => {
  try {
      const { data } = await api.get(`expenses`, {
        headers: { Authorization: `Bearer ${token}` }

      });
      console.log(data);
      setExpenses(data);
    }
    catch (err) {
      console.error(err);
      setExpenses([]);
    }
  }

  useEffect(() => {
    handleFetchExpenses();
  }, []);

  return (
    <table className="w-full border-collapse border border-gray-200">
      <thead>
        <tr>
          <th className="px-4 py-2 text-left border-b border-gray-200">Title</th>
          <th className="px-4 py-2 text-left border-b border-gray-200">Amount</th>
          <th className="px-4 py-2 text-left border-b border-gray-200">Category</th>
          <th className="px-4 py-2 text-left border-b border-gray-200">Actions</th>
        </tr>
      </thead>
      <tbody>
        {expenses.length > 0 ? (
          expenses.map((expense, id) => (
            <tr
              key={id}
              className={id % 2 === 0 ? "bg-gray-100" : "bg-white"}
            >
              <td className="px-4 py-2 border-b border-gray-200">{expense.expenseTitle}</td>
              <td className="px-4 py-2 border-b border-gray-200">{expense.expenseAmount}</td>
              <td className="px-4 py-2 border-b border-gray-200">{expense.expenseCategory}</td>
              <td className="px-4 py-2 border-b border-gray-200">
                {/* actions buttons here */}
              </td>
            </tr>
          ))
        ) : (
          <tr>
            <td colSpan="4" className="text-center py-4">
              No expenses found.
            </td>
          </tr>
        )}
      </tbody>
    </table>

  )
}

export default ExpensesList;