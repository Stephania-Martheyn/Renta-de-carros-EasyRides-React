import React, { useContext } from 'react'
import "./Administrador.css"
import { Formik, Form, Field, ErrorMessage, FieldArray } from "formik";
import axios from "axios";
import { Link } from 'react-router-dom';
import { useNavigate } from "react-router-dom";
import { TokenUsuarioContext } from '../../../Context/TokenUsuarioContext';
import { SearchBar } from '../Buscador/SearchBar';
import useFetch from '../../../Hooks/useFetch';




function Administrador(){

  const navigate = useNavigate();
  const { tokenUsuario, setTokenUsuario } = useContext(TokenUsuarioContext);
  const urlCrearProducto =
    "http://ec2-3-145-197-27.us-east-2.compute.amazonaws.com:8080/productos";
  const urlCiudades =
    "http://ec2-3-145-197-27.us-east-2.compute.amazonaws.com:8080/ciudades";
  const urlCategorias =
      "http://ec2-3-145-197-27.us-east-2.compute.amazonaws.com:8080/categorias";
  const urlCaracteristicas = "http://ec2-3-145-197-27.us-east-2.compute.amazonaws.com:8080/caracteristicas"
  
  
  const { data } = useFetch(urlCiudades);
  const { data: dataCategorias } = useFetch(urlCategorias)
  const { data: dataCaracteristicas } = useFetch(urlCaracteristicas); 
  


  const crearProducto = (objetoUsuario) => {
    axios({
      method: "post",
      url: urlCrearProducto,
      data: objetoUsuario,
      mode: "no-cors",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${tokenUsuario}`,
      },
    })
      .then(function (response) {
        //handle success
        console.log(response);
        navigate("/");
      })
      .catch(function (response) {
        console.log(response);
        
        //handle error
      });
  };  
  return (
    <div>
      <div className="header-admin">
        <h2 className="header-admin-titulo">Administracion</h2>
      </div>
      <div className="formulario">
        <Formik
          initialValues={{
            titulo: "1",
            descripcion: "1",
            url: "1",
            ciudad: {
              id: "1",
            },
            categoria: {
              id: "1",
            },
            caracteristicas: [
              {
                id: "",
              },
            ],
            imagenes: [
              {
                url: "1",
              },
            ],
          }}
          onSubmit={(values) => {
            // console.log(tokenUsuario);
            crearProducto(values);
            // Comparar con mi base de datos de usuarios registrado

            //VER ESTO PARA RESETEAR FORM
          }}
          // validate={(valores) => {
          //   let errores = {};

          //   //validacion titulo
          //   if (!valores.titulo) {
          //     errores.titulo = "Por favor ingresa el titulo";
          //   } else if (!/^.{4,12}$/.test(valores.titulo)) {
          //     errores.titulo =
          //       "Por favor ingrese un titulo de 4 a 12 caracteres";
          //   }

          //   //validacion descripcion
          //   if (!valores.descripcion) {
          //     errores.descripcion = "Por favor ingresa tu contraseña";
          //   } else if (!/^.{4,12}$/.test(valores.descripcion)) {
          //     errores.descripcion =
          //       "Por favor ingrese una contraseña de 4 a 12 caracteres";
          //   }

          //   // validacion url
          //   if (!valores.url) {
          //     errores.url = "Por favor ingresa tu contraseña";
          //   } else if (!/^.{4,12}$/.test(valores.url)) {
          //     errores.url =
          //       "Por favor ingrese una contraseña de 4 a 12 caracteres";
          //   }
          //   // validacion ciudad
          //   if (!valores.ciudad) {
          //     errores.ciudad = "Por favor ingresa tu contraseña";
          //   } else if (!/^.{4,12}$/.test(valores.ciudad)) {
          //     errores.ciudad =
          //       "Por favor ingrese una contraseña de 4 a 12 caracteres";
          //   }
          //   return errores;

          // crear validacion logeo
          // }}
        >
          {({ errors }) => (
            <div className="">
              <Form className="formulario-admin">
                <div className="formulario-admin-primera-linea">
                  <div>
                    <label htmlFor="titulo">Nombre producto</label>
                    <Field
                      type="text"
                      name="titulo"
                      placeholder="Ej: Toyota Etios"
                      id="titulo"
                    />
                    <ErrorMessage
                      name="titulo"
                      component={() => (
                        <div className="error">{errors.titulo}</div>
                      )}
                    />
                  </div>
                  <div>
                    <label htmlFor="categoria">Categoria</label>
                    <Field as="select" name="categoria.id" id="categoria">
                      {
                        dataCategorias &&
                          dataCategorias.categorias.map((cat) => (
                            <option value={cat.id}>{cat.titulo}</option>
                          ))
                      }
                    </Field>

                    <ErrorMessage
                      name="url"
                      component={() => (
                        <div className="error">{errors.descripcion}</div>
                      )}
                    />
                  </div>
                </div>
                <div>
                  <label htmlFor="descripcion">Descripcion</label>
                  <Field
                    type="text"
                    name="descripcion"
                    placeholder="Ej: El Toyota Etios es un auto que ..."
                    id="descripcion"
                  />
                  <ErrorMessage
                    name="descripcion"
                    component={() => (
                      <div className="error">{errors.descripcion}</div>
                    )}
                  />
                </div>

                <div>
                  <label htmlFor="url">Url</label>
                  <Field
                    type="text"
                    name="url"
                    // placeholder="Ej: El Toyota Etios es un auto que ..."
                    id="url"
                  />
                  <ErrorMessage
                    name="url"
                    component={() => (
                      <div className="error">{errors.descripcion}</div>
                    )}
                  />
                </div>
                <div>
                  {/* <label htmlFor="ciudad.id">Ciudad</label>
                  <Field
                    type="text"
                    name="ciudad.id"
                    // placeholder="Ej: El Toyota Etios es un auto que ..."
                    id="ciudad"
                  /> */}
                  {/* --------------------------------------------- */}
                  <label htmlFor="ciudad">Ciudad</label>

                  <Field as="select" name="ciudad.id">
                    {
                      // .map((ciudad) => player.id !== props.values.player1)
                      data &&
                        data.ciudad.map((ciudad) => (
                          <option value={ciudad.id}>{ciudad.nombre}</option>
                        ))
                    }
                  </Field>
                  {/* --------------------------------------------- */}
                  <ErrorMessage
                    name="ciudad"
                    component={() => (
                      <div className="error">{errors.descripcion}</div>
                    )}
                  />
                </div>

                {/* -----------------------checkbox--------------------------- */}

                {/* <div>
                  <label htmlFor="caracteristicas">Caracteristicas</label>
                  <Field
                    type="text"
                    name="caracteristicas[].id"
                    // placeholder="Ej: El Toyota Etios es un auto que ..."
                    id="caracteristicas"
                  />
                  <ErrorMessage
                    name="url"
                    component={() => (
                      <div className="error">{errors.descripcion}</div>
                    )}
                  />
                </div> */}
                {dataCaracteristicas &&
                  dataCaracteristicas.caracteristicas.map((carac) => (
                    <>
                      <label htmlFor="caracteristicas">{carac.titulo}</label>
                      <Field
                        type="checkbox"
                        name="caracteristicas"
                        key={carac.id}
                        id={carac.id}
                        value={carac.id}
                      />
                    </>
                  ))}
                {/* ----------------------------checkbox---------------- */}
                <div className="imagenes">
                  <label htmlFor="imagenes">Imagenes</label>
                  <Field
                    type="text"
                    name="imagenes[0].url"
                    // placeholder="Ej: El Toyota Etios es un auto que ..."
                    id="imagenes"
                  />
                  <ErrorMessage
                    name="imagenes"
                    component={() => (
                      <div className="error">{errors.descripcion}</div>
                    )}
                  />
                  <label htmlFor="imagenes">Imagenes</label>
                  <Field
                    type="text"
                    name="imagenes[1].url"
                    // placeholder="Ej: El Toyota Etios es un auto que ..."
                    id="imagenes"
                  />
                  <ErrorMessage
                    name="imagenes"
                    component={() => (
                      <div className="error">{errors.descripcion}</div>
                    )}
                  />
                  <label htmlFor="imagenes">Imagenes</label>
                  <Field
                    type="text"
                    name="imagenes[2].url"
                    // placeholder="Ej: El Toyota Etios es un auto que ..."
                    id="imagenes"
                  />
                  <ErrorMessage
                    name="imagenes"
                    component={() => (
                      <div className="error">{errors.descripcion}</div>
                    )}
                  />
                  <label htmlFor="imagenes">Imagenes</label>
                  <Field
                    type="text"
                    name="imagenes[3].url"
                    // placeholder="Ej: El Toyota Etios es un auto que ..."
                    id="imagenes"
                  />
                  <ErrorMessage
                    name="imagenes"
                    component={() => (
                      <div className="error">{errors.descripcion}</div>
                    )}
                  />
                  <label htmlFor="imagenes">Imagenes</label>
                  <Field
                    type="text"
                    name="imagenes[4].url"
                    // placeholder="Ej: El Toyota Etios es un auto que ..."
                    id="imagenes"
                  />
                  <ErrorMessage
                    name="imagenes"
                    component={() => (
                      <div className="error">{errors.descripcion}</div>
                    )}
                  />
                </div>

                <div className="Buttom">
                  <button type="submit">Crear Producto</button>
                </div>
              </Form>
            </div>
          )}
        </Formik>
      </div>
    </div>
  );
}

export  {Administrador}