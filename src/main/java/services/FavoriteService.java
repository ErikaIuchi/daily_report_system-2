package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.FavoriteConverter;
import actions.views.FavoriteView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Favorite;

public class FavoriteService extends ServiceBase {

	/**
	 * いいねした人一覧表示用
	 * @param page
	 * @return 一覧画面に表示するデータのリスト
	 */
	public List<FavoriteView> getMinePerPage(ReportView report, int page){

		List<Favorite> favorites = em.createNamedQuery(JpaConst.Q_FAV_GET_ALL_MINE, Favorite.class)
				.setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
				.setFirstResult(JpaConst.ROW_PER_PAGE * (page -1))
				.setMaxResults(JpaConst.ROW_PER_PAGE)
				.getResultList();
		return FavoriteConverter.toViewList(favorites);
	}

	/**
	 * いいねの有無判定用（検索結果が０ならまだいいねをしていないと判断）
	 * @param employee
	 * @param report
	 * @return
	 */
	 public long isFavorite(EmployeeView employee, ReportView report) {

	        long count = (long) em.createNamedQuery(JpaConst.Q_FAV_COUNT_ALL_MINE, Long.class)
	                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, EmployeeConverter.toModel(employee))
	                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
	                .getSingleResult();

	        return count;
	    }

	 /**
	  * いいねした人一覧ページネーション
	  */
	 public List<FavoriteView> getAllPerPage(int page){

		 List<Favorite> favorites = em.createNamedQuery(JpaConst.Q_FAV_GET_ALL, Favorite.class)
				 .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
				 .setMaxResults(JpaConst.ROW_PER_PAGE)
				 .getResultList();
		 return FavoriteConverter.toViewList(favorites);
	 }

	 /**
	  * いいねをDBに登録
	  */
	 public void create(FavoriteView fv){
		 LocalDateTime ldt = LocalDateTime.now();
		 fv.setCreatedAt(ldt);
		 fv.setUpdatedAt(ldt);
		 createInternal(fv);
	 }

	 /**
	  * いいねを一見登録する
	  */
	 private void createInternal(FavoriteView fv) {

		 em.getTransaction().begin();
		 em.persist(FavoriteConverter.toModel(fv));
		 em.getTransaction().commit();
	 }

}
