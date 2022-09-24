import React,{useContext} from 'react'
import "./Avatar.css";
import AuthContext from "../../../Context/AuthContext";

export const Avatar = () => {
  const { auth, setAuth } = useContext(AuthContext);

  const cerrarSesion = () => {
    setAuth(null)
  }
  return (
    <div className='avatar'>
      <p className='avatar-nombre'>{auth.nombre}</p>
      <button className='avatar-sesion' onClick={ () => cerrarSesion()}>Cerrar Sesion</button>
    </div>
  );
}
