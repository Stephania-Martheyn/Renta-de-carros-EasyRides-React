// ponemos funciones que modifiquen el dentro de useInitialState

import React, { useState } from "react";

const initialState = {
  userRegister: [
    {
      nombre: "da",
      apellido: "da",
      correo: "da@gmail.com",
      contraseña: "12345678",
      confirmarContraseña: "12345678",
    },
  ],
};

export const useInitialState = () => {
  const [state, setState] = useState(initialState);
  // modifica estado de useRegister

  const setValores = (info) => {
    setState((prevState) => ({
      ...prevState,
      userRegister: [...prevState.userRegister, info],
    }));
    console.log(state);
  };
  return {
    setValores,
    state,
  };
};
