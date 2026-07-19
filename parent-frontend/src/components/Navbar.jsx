import { useNavigate } from "react-router-dom";

const Navbar = () => {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem("token");
    navigate("/role-selection");
  };

  return (
    <div className="navbar">
      <h3>University Portal</h3>
      <button onClick={logout}>Logout</button>
    </div>
  );
};

export default Navbar;