import { useReactTable } from "@tanstack/react-table";

const TD = (props) => {
  if (props.column.sticky) {
    return <th {...props} />;
  }
  return <td {...props} />;
};

function Table({ columns = [], data = [], ...tableProps }) {
  const table = useReactTable({ columns, data });
  return (
    <table {...tableProps}>
      <thead></thead>
      <tbody></tbody>
    </table>
  );
}

export default Table;
