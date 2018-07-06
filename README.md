# Recruitment Agent Fee Calculator #
###  Spring-boot, H2, Thyme-leaf, Bootstrap - Demo App ###
This is a small demo application of calculating a recruitment agent's fee based on following requirements.

There are two types of resources for hiring 
  1. Masons.
  2. Carpenters.
  
* Agents can bring in resource as individuals or as a group of 5 members with the same skill group.

* For every successfully hired mason, the agent gets 200$ and for every successfully hired carpenter, the agent is payed 250$.

* For each group hired (A set of 5 individuals from the same skill group) an additional 10% commission is payed for the agent. 
```
Ex: If a given agent recruited 5 masons.
Fee = (5*200 + (5*200)*0.1)
```
* When calculating the fee for agents, hired people should be grouped together automatically and the commission should be calculated for each group and added to the fee
correctly for 1 month period.
