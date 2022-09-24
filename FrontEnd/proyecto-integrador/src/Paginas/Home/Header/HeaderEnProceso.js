import { useRef, useContext } from "react";
import React from "react";
import "./HeaderEnProceso.css";
import logoeasyrides from "./logoeasyrides.png";
//import { FaBars, FaTimes } from "react-icons/fa";
import { Link } from "react-router-dom";
import { useInitialState } from "../../../Hooks/useInitialState";
import AuthContext from "../../../Context/AuthContext";
//import { BotonesLoginRegister } from "./BotonesLoginRegister";
//import { Avatar } from "./Avatar";
import menuHamburguesa from "./menuHamburguesa.svg";

const Header = () => {
  const navRef = useRef();
  const initialState = useInitialState();

  const { auth } = useContext(AuthContext);

  const mostrarNavBar = () => {
    // navRef.style.display = "";
    navRef.current.classList.toggle("responsive-header");
  };
  return (
    <header className="header">
      <Link to="/">
        <img className="header-logo" src={logoeasyrides} alt="logo" />
      </Link>

      {/*Desarrollo de los botones con menu hamburguesa*/}

      <label for="menu" className="nav_label">
        <img
          className="menuHamburguesa"
          src={menuHamburguesa}
          alt="hamburgues"
        />
      </label>
      <input type="checkbox" id="menu" className="nav_input"></input>
      <div className="nav_menu">
        <Link to="/login">
          <button className="nav_item" id="Iniciar-sesion">
            Iniciar sesión
          </button>
        </Link>
        <Link to="/registro">
          <button className="nav_item" id="Crear-cuenta">
            Crear cuenta
          </button>
        </Link>
      </div>
    </header>
  );
};

export default Header;
