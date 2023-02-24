import React, { useState, useEffect, useRef } from 'react';
import styled from 'styled-components';
import filtericon from '../../img/filter_ic.svg';
import Filter from './Filter';

const Section = styled.button`
	button {
		width: 71px;
		height: 35px;
		color: #39739d;
		background-color: #e1ecf4;
		border-radius: 3px;
		font-size: 13px;
		margin-bottom: 20px;
		border: 1px solid #39739d;
	}

	button:hover {
		color: #2c5877;
		background-color: #b3d3ea;
	}

	.filterinfo {
		height: 0;
		opacity: 0;
		transition: opacity 0.3s ease-in-out, height 0.3s 0.3s ease-in-out;
	}

	.animated {
		opacity: 1;
		transition: height 0.3s ease-in-out, opacity 0.3s 0.3s ease-in-out;
	}
`;

const FilterImg = styled.img`
	width: 10px;
	height: 10px;
`;

const FilterButton = () => {
	const [toggle, setToggle] = useState(false);
	const [heightEl, setHeightEl] = useState();

	const refHeight = useRef();

	useEffect(() => {
		console.log(refHeight);
		setHeightEl(`${refHeight.current.scrollHeight}px`);
	}, []);

	const toggleState = () => {
		setToggle(!toggle);
	};

	console.log(toggle);

	return (
		<Section>
			<button onClick={toggleState}>
				<FilterImg src={filtericon}></FilterImg>
				Filter
			</button>
			<div
				className={toggle ? 'filterinfo animated' : 'filterinfo'}
				style={{ height: toggle ? `${heightEl}` : '0px' }}
				ref={refHeight}
			>
				<Filter></Filter>
			</div>
		</Section>
	);
};

export default FilterButton;
