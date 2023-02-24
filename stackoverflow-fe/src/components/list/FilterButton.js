import React from 'react';
import styled from 'styled-components';
import Filtericon from '../../img/filter_ic.svg';

const Section = styled.section`
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
`;

const FilterButton = () => {
	return (
		<Section>
			<button>
				<img src={Filtericon} />
				Filter
			</button>
		</Section>
	);
};

export default FilterButton;
