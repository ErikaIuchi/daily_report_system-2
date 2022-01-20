package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * いいねデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_FAV)
@NamedQueries({
	@NamedQuery(
			name = JpaConst.Q_FAV_GET_ALL,
			query = JpaConst.Q_FAV_GET_ALL_DEF),
	@NamedQuery(
			name = JpaConst.Q_FAV_COUNT_ALL_MINE,
			query = JpaConst.Q_FAV_COUNT_ALL_MINE_DEF),
	@NamedQuery(
			name = JpaConst.Q_FAV_GET_ALL_MINE,
			query = JpaConst.Q_FAV_GET_ALL_MINE_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Favorite {

	/**
	 * id
	 */
	@Id
	@Column(name = JpaConst.FAV_COL_ID)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * いいねした人の従業員ID
	 */
	@ManyToOne
	@JoinColumn(name = JpaConst.FAV_COL_EMP, nullable = false)
	private Employee employee;

	/**
	 * いいねした日報のID
	 */
	@ManyToOne
	@JoinColumn(name = JpaConst.FAV_COL_REP, nullable = false)
	private Report report;

	/**
	 * いいねした日時
	 */
	@Column(name = JpaConst.FAV_COL_CREATED_AT, nullable = false)
	private LocalDateTime createdAt;

	/**
	 * 更新日時
	 */
	@Column(name = JpaConst.FAV_COL_UPDATED_AT, nullable = false)
	private LocalDateTime updatedAt;

}
