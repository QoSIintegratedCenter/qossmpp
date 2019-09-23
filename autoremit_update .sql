--
-- Table structure for table `qos_smpp_request`
--
CREATE TABLE `qos_smpp_request` (
  `id` bigint(20) NOT NULL,
  `resquestdate` tinyblob,
  `charset` varchar(255) DEFAULT NULL,
  `from_param` varchar(255) DEFAULT NULL,
  `text_param` varchar(255) DEFAULT NULL,
  `to_param` varchar(255) DEFAULT NULL,
  `resquest_date` tinyblob
) ENGINE=MyISAM DEFAULT CHARSET=latin1;


--
-- Table structure for table `qos_smpp_response`
--

CREATE TABLE `qos_smpp_response` (
  `id` bigint(20) NOT NULL,
  `requests_id` bigint(20) DEFAULT NULL,
  `command_id` int(11) NOT NULL,
  `command_status` int(11) NOT NULL,
  `connexion_size` int(11) NOT NULL,
  `message_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `response_date` tinyblob,
  `result_message` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
--
-- Indexes for dumped tables
--

--
-- Indexes for table `qos_smpp_request`
--
ALTER TABLE `qos_smpp_request`
ADD PRIMARY KEY (`id`);

--
-- Indexes for table `qos_smpp_response`
--
ALTER TABLE `qos_smpp_response`
ADD PRIMARY KEY (`id`),
ADD KEY `FK5h1ko4j4uu3go3q199hyhb9dy` (`requests_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `qos_smpp_request`
--
ALTER TABLE `qos_smpp_request`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=343;
--
-- AUTO_INCREMENT for table `qos_smpp_response`
--
ALTER TABLE `qos_smpp_response`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;


create table qos_smpp_users AS SELECT * FROM users_profile;