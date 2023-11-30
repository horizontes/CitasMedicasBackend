package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GenericServices {

    void init(){

        Gson gson = new Gson();

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(
                             new FileInputStream("src/main/resources/static/especialidades.json"), "UTF-8"))
        ) {

            Especialidad[] cs = gson.fromJson(reader, Especialidad[].class);
            Arrays.stream(cs).forEach(e -> {
                e.setCreacion(new Date(System.currentTimeMillis()));
                e.setActualizacion(new Date(System.currentTimeMillis()));
                e.setEstado(1);
                especialidadRepositorio.save(e);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(
                             new FileInputStream("src/main/resources/static/medicos.json"), "UTF-8"))
        ){

            Medico[] cs = gson.fromJson(reader, Medico[].class);
            Arrays.stream(cs).forEach(e -> {
                e.setCreacion(new Date(System.currentTimeMillis()));
                e.setActualizacion(new Date(System.currentTimeMillis()));
                e.setEstado(1);
                medicoRepositorio.save(e);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(
                             new FileInputStream("src/main/resources/static/roles.json"), "UTF-8"))
        ){

            Rol[] cs = gson.fromJson(reader, Rol[].class);
            Arrays.stream(cs).forEach(e -> {
                e.setCreacion(new Date(System.currentTimeMillis()));
                e.setActualizacion(new Date(System.currentTimeMillis()));
                e.setEstado(1);
                rolRepositorio.save(e);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(
                             new FileInputStream("src/main/resources/static/sedes.json"), "UTF-8"))
        ){

            Sede[] cs = gson.fromJson(reader, Sede[].class);
            Arrays.stream(cs).forEach(e -> {
                e.setCreacion(new Date(System.currentTimeMillis()));
                e.setActualizacion(new Date(System.currentTimeMillis()));
                e.setEstado(1);
                sedeRepositorio.save(e);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(
                             new FileInputStream("src/main/resources/static/usuarios.json"), "UTF-8"))
        ){

            Usuario[] cs = gson.fromJson(reader, Usuario[].class);
            Arrays.stream(cs).forEach(e -> {
                e.setCreacion(new Date(System.currentTimeMillis()));
                e.setActualizacion(new Date(System.currentTimeMillis()));
                e.setEstado(1);
                e.setClave("123456");
                usuarioRepositorio.save(e);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(
                             new FileInputStream("src/main/resources/static/usuarios_2.json"), "UTF-8"))
        ){

            Usuario[] cs = gson.fromJson(reader, Usuario[].class);
            Arrays.stream(cs).forEach(e -> {
                e.setCreacion(new Date(System.currentTimeMillis()));
                e.setActualizacion(new Date(System.currentTimeMillis()));
                e.setEstado(1);
                e.setClave("123456");
                usuarioRepositorio.save(e);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        generarCita();

    }

    @Autowired
    CitasRepositorio citasRepositorio;

    @Autowired
    EspecialidadRepositorio especialidadRepositorio;

    @Autowired
    MedicoRepositorio medicoRepositorio;

    @Autowired
    RolRepositorio rolRepositorio;

    @Autowired
    SedeRepositorio sedeRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    private void generarCita(){

        List<Usuario> usuarios = StreamSupport.stream(
                usuarioRepositorio.findAll().spliterator(),
                false).toList();

        List<Especialidad> especialidades = StreamSupport.stream(
                especialidadRepositorio.findAll().spliterator(),
                false).toList();

        List<Sede> sedes = StreamSupport.stream(
                sedeRepositorio.findAll().spliterator(),
                false).toList();

        List<Medico> medicos = StreamSupport.stream(
                medicoRepositorio.findAll().spliterator(),
                false).toList();

        var rolesSet = StreamSupport.stream(
                rolRepositorio.findAll().spliterator(),
                false).collect(Collectors.toSet());

/**
 var medicosSet = StreamSupport.stream(
 medicoRepositorio.findAll().spliterator(),
 false).collect(Collectors.toSet());

 var rolesSet = StreamSupport.stream(
 rolRepositorio.findAll().spliterator(),
 false).collect(Collectors.toSet());

 var especialidadesSet = StreamSupport.stream(
 especialidadRepositorio.findAll().spliterator(),
 false).collect(Collectors.toSet());

 sedes.forEach(s -> {
 s.setEspecialidades(especialidadesSet);
 sedeRepositorio.save(s);
 });

 especialidades.forEach(e -> {
 e.setMedicos(medicosSet);
 especialidadRepositorio.save(e);
 });
 **/
        usuarios.forEach(u ->{
            if (Objects.isNull(u.getRol()) || u.getRol().isEmpty()) {
                u.setRol(rolesSet);
                usuarioRepositorio.save(u);
            }
        });

        List<String> horas = List.of("07:00", "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"
                , "20:00", "21:00");

        usuarios.forEach(u -> {
            final int n = getRandomNumberUsingNextInt(10, 20);
            for (int i = 0; i < n; i++) {
                Especialidad e = especialidades.get(getRandomNumberUsingNextInt(0, especialidades.size()));
                Sede s = sedes.get(getRandomNumberUsingNextInt(0, sedes.size()));
                Medico m = medicos.get(getRandomNumberUsingNextInt(0, medicos.size()));
                String hora = horas.get(getRandomNumberUsingNextInt(0, horas.size()));
                Citas c = new Citas();

                c.setCreacion(new Date(System.currentTimeMillis()));
                c.setActualizacion(new Date(System.currentTimeMillis()));
                c.setEstado(1);

                c.setDuracion(1);
                c.setConfirmado(true);
                c.setHora(hora);
                c.setFecha(getGenerateDates());
                c.setEspecialidad(e);
                c.setSede(s);
                c.setMedico(m);
                c.setUsuario(u);

                citasRepositorio.save(c);

            }
        });

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(
                             new FileInputStream("src/main/resources/static/usuarios_blank.json"), "UTF-8"))
        ){

            Usuario[] cs = new Gson().fromJson(reader, Usuario[].class);
            Arrays.stream(cs).forEach(e -> {
                e.setCreacion(new Date(System.currentTimeMillis()));
                e.setActualizacion(new Date(System.currentTimeMillis()));
                e.setEstado(1);
                e.setClave("123456");
                usuarioRepositorio.save(e);
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        return new Random().nextInt(max - min) + min;
    }

    public Date getGenerateDates(){
        return Date.valueOf(
                LocalDate.of(2024, getRandomNumberUsingNextInt(1, 12),
                        getRandomNumberUsingNextInt(1, 30)));

    }

}
