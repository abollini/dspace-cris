/**
 * The contents of this file are subject to the license and copyright
 * detailed in the LICENSE and NOTICE files at the root of the source
 * tree and available online at
 * 
 * http://www.dspace.org/license/
 * 
 * The document has moved 
 * <a href="https://svn.duraspace.org/dspace/licenses/LICENSE_HEADER">here</a>
 */
package it.cilea.hku.authority.model.dynamicfield;

import it.cilea.hku.authority.model.ResearcherPage;
import it.cilea.osd.jdyna.model.AnagraficaSupport;
import it.cilea.osd.jdyna.model.Property;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Index;

@Entity
@Table(name="model_rp_jdyna_prop", 
        uniqueConstraints = {@UniqueConstraint(columnNames={"position","typo_id","parent_id"})})
@NamedQueries( {
	@NamedQuery(name = "RPProperty.findPropertyByPropertiesDefinition", query = "from RPProperty where typo = ? order by position"),
	@NamedQuery(name = "RPProperty.findAll", query = "from RPProperty order by id"),	
	@NamedQuery(name = "RPProperty.findPropertyByParentAndTypo", query = "from RPProperty  where (parent.id = ? and typo.id = ?) order by position"),	
	@NamedQuery(name = "RPProperty.deleteAllPropertyByPropertiesDefinition", query = "delete from RPProperty property where typo = ?)")
})
public class RPProperty extends Property<RPPropertiesDefinition> {
	
	@ManyToOne(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SELECT)	
	private RPPropertiesDefinition typo;
	
	
	@ManyToOne(targetEntity = ResearcherPage.class)	
	@Index(name = "model_rp_jdyna_prop_parent_id")
	private ResearcherPage parent;


	@Override
	public RPPropertiesDefinition getTypo() {
		return typo;
	}

	@Override
	public void setTypo(RPPropertiesDefinition propertyDefinition) {
		this.typo = propertyDefinition;		
	}

	@Override
	public ResearcherPage getParent() {
		return parent;
	}

	@Override
	public void setParent(
			Object parent) {
		
		this.parent = (ResearcherPage)parent;
		
	}

}
