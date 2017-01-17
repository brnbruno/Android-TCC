package com.bruno.pizzaplanety;

public class Agenda {
    //private variables
    int _id;
    String _nome;
    String _cpf;
    String _email;
    String _fone;
    String _sexo;
    String _cep;
    String _cidade;
    String _endereco;
    String _bairro;
    String _uf;
    String _nr;
    String _senha;
    String _complemento;
    // Empty constructor
    public Agenda(){
         
    }
    // constructor
    public Agenda(int _id, String _nome,String _cpf, String _email, String _fone, String _sexo,String _cep,String _cidade,String _endereco,
    					String _bairro,String _complemento,String _uf, String _nr,String _senha){
        this._id    = _id;
        this._nome  = _nome;
        this._cpf   =_cpf;
        this._email = _email;
        this._fone  = _fone;
        this._sexo  = _sexo;
        this._cep	= _cep;
        this. _cidade = _cidade;
        this. _endereco = _endereco;
        this. _bairro = _bairro;
        this._complemento =_complemento;
        this. _uf = _uf;
        this. _nr = _nr;
        this. _senha = _senha;
    }
    
    // getting ID
    public int getID(){
        return this._id;
    }
     
    // setting id
    public void setID(int id){
        this._id = id;
    }
     
    // getting nome
    public String getNome(){
        return this._nome;
    }
     
    // setting name
    public void setNome(String nome){
        this._nome = nome;
    }
    public String getCpf(){
    	return this._cpf;
    }
    public void setCpf(String cpf){
    	this._cpf = cpf;
    }

    // getting email
    public String getEmail(){
        return this._email;
    }
     
    // setting email
    public void setEmail(String email){
        this._email = email;
    }

    // getting fone
    public String getFone(){
        return this._fone;
    }
     
    // setting fone
    public void setFone(String fone){
        this._fone = fone;
    }
    
    // getting sexo
    public String getSexo(){
        return this._sexo;
    }
     
    // setting sexo
    public void setSexo(String sexo){
        this._sexo = sexo;
    }
    
    
    public String getCep(){
        return this._cep;
    }
     
    public void setCep(String cep){
        this._cep = cep;
    }
    
    public String getCidade(){
        return this._cidade;
    }
     
    public void setCidade(String cidade){
        this._cidade = cidade;
    }
    public String getEndereco(){
    	return this._endereco;
    }
    public void setEndereco(String endereco){
        this._endereco = endereco;
    }
    public String getBairro(){
    	return this._bairro;
    }
    public void setBairro(String bairro){
    	this._bairro = bairro;
    }
    public String getUf(){
    	return this._uf;
    }
    public void setUf(String uf){
    	this._uf = uf;
    }
    public String getNr(){
    	return this._nr;
    }
    public void setNr(String nr){
    	this._nr = nr;
    }
    public String getSenha(){
    	return this._senha;
    }
    public void setSenha(String senha){
    	this._senha = senha;
    }
    public String getComplemento(){
    	return this._complemento;
    }
	public void setComplemento(String complemento) {
		this._complemento = complemento;		
	}
}
