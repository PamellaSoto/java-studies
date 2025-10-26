import ExpensesList from "../components/ExpensesList";

function Dashboard() {   
  // handle logout (clear token and exit to login)

  return (
    <main className="bg-amber-100 p-20 min-h-dvh">
      <div className="bg-white rounded-xl p-10">
        <h1 className="text-3xl font-bold pb-4 mb-4 border-b border-gray-200">Expenses tracker</h1>
        <div>
          {/* logout button here */}
          {/* create task btn here */}
        </div>
        <ExpensesList />
      </div>
    </main>
  )
}

export default Dashboard
