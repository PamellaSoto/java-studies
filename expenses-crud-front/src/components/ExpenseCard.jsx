const ExpenseCard = ( {title, amount, category} ) => {
  return (
    <tr>
      <td>{title}</td>
      <td>{amount}</td>
      <td>{category}</td>
      <td>{/* edit and delete task here */}</td>
    </tr>
  )
}

export default ExpenseCard;