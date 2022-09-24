import React, { useContext } from 'react'
import { useLocation, Navigate } from 'react-router'
import AuthContext from '../../Context/AuthContext'
import { Login } from '../Login/Login';
import Reservas from './Reservas';

export const RequerirAuth = () => {
    const { auth } = useContext(AuthContext);
    const location = useLocation()
  return (
    auth ? <Reservas /> :
    <Navigate to="/login" state={{from: location}} replace/>

  )
}
