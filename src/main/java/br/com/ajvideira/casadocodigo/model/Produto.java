package br.com.ajvideira.casadocodigo.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="produto")
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="titulo")
	private String titulo;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="paginas")
	private Integer paginas;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_lancamento")
	private Calendar dataLancamento;
	
	@ElementCollection
	@CollectionTable(name="produto_precos", joinColumns = {@JoinColumn(name = "produto_id", referencedColumnName = "id")})
	private List<Preco> precos; 
	
	private String sumarioPath;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Integer getPaginas() {
		return paginas;
	}
	
	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}
	
	public List<Preco> getPrecos() {
		return precos;
	}

	public void setPrecos(List<Preco> precos) {
		this.precos = precos;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getSumarioPath() {
		return sumarioPath;
	}

	public void setSumarioPath(String sumarioPath) {
		this.sumarioPath = sumarioPath;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + ", paginas=" + paginas
				+ ", dataLancamento=" + dataLancamento + ", precos=" + precos + ", sumarioPath=" + sumarioPath + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataLancamento == null) ? 0 : dataLancamento.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((paginas == null) ? 0 : paginas.hashCode());
		result = prime * result + ((precos == null) ? 0 : precos.hashCode());
		result = prime * result + ((sumarioPath == null) ? 0 : sumarioPath.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (dataLancamento == null) {
			if (other.dataLancamento != null)
				return false;
		} else if (!dataLancamento.equals(other.dataLancamento))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (paginas == null) {
			if (other.paginas != null)
				return false;
		} else if (!paginas.equals(other.paginas))
			return false;
		if (precos == null) {
			if (other.precos != null)
				return false;
		} else if (!precos.equals(other.precos))
			return false;
		if (sumarioPath == null) {
			if (other.sumarioPath != null)
				return false;
		} else if (!sumarioPath.equals(other.sumarioPath))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
}
