-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-05-2015 a las 19:04:19
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `spotifydb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `song`
--

CREATE TABLE IF NOT EXISTS `song` (
`id_song` int(11) NOT NULL,
  `name_song` varchar(100) NOT NULL,
  `genre` varchar(100) NOT NULL,
  `album` varchar(100) NOT NULL,
  `artist` varchar(100) NOT NULL,
  `URL` varchar(200) NOT NULL,
  `num_plays` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `song`
--

INSERT INTO `song` (`id_song`, `name_song`, `genre`, `album`, `artist`, `URL`, `num_plays`) VALUES
(32, 'Test', 'Test2', 'Test3', 'Test4', '5_second_song.mp3', 3),
(33, 'Test2', 'Test2', 'Test3', 'Test4', '5_second_song.mp3', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id_user` int(11) NOT NULL,
  `login` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `date_register` varchar(20) NOT NULL,
  `last_access` varchar(20) NOT NULL,
  `num_songlists` int(11) NOT NULL DEFAULT '0',
  `num_songs` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id_user`, `login`, `password`, `date_register`, `last_access`, `num_songlists`, `num_songs`) VALUES
(8, 'Albert', 'Albert123', '23/05/2015', '24/05/2015', 0, 1),
(9, 'Ignasi', 'Ignasi123', '23/05/2015', '23/05/2015', 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usersonglist`
--

CREATE TABLE IF NOT EXISTS `usersonglist` (
`id_list` int(11) NOT NULL,
  `name_list` varchar(100) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_song` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `usersonglist`
--

INSERT INTO `usersonglist` (`id_list`, `name_list`, `id_user`, `id_song`) VALUES
(9, 'p1', 8, 0),
(21, 'p1', 8, 32),
(22, 'partylist', 8, 32),
(23, 'partylist', 8, 33);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `song`
--
ALTER TABLE `song`
 ADD PRIMARY KEY (`id_song`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id_user`), ADD UNIQUE KEY `login` (`login`);

--
-- Indices de la tabla `usersonglist`
--
ALTER TABLE `usersonglist`
 ADD PRIMARY KEY (`id_list`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `song`
--
ALTER TABLE `song`
MODIFY `id_song` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de la tabla `usersonglist`
--
ALTER TABLE `usersonglist`
MODIFY `id_list` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
