import React, { createContext, useState } from "react";

export const TokenUsuarioContext = createContext({});

export const TokenUsuarioProvider = ({ children }) => {
  const [tokenUsuario, setTokenUsuario] = useState();

  return(
    <TokenUsuarioContext.Provider value={{ tokenUsuario, setTokenUsuario }}>
      {children}
    </TokenUsuarioContext.Provider>
  );
  
};
