export default function EmployeeForm({ onSubmit }) {
    const [form, setForm] = useState({ name: '', email: '', salary: 0 });
  
    const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });
  
    return (
      <form onSubmit={(e) => { e.preventDefault(); onSubmit(form); }}>
        <input name="name" onChange={handleChange} />
        <input name="email" onChange={handleChange} />
        <input name="salary" type="number" onChange={handleChange} />
        <button type="submit">Add</button>
      </form>
    );
  }
  